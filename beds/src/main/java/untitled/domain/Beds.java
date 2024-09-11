package untitled.domain;

import untitled.BedsApplication;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;


@Entity
@Table(name="Beds_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//<<< DDD / Aggregate Root
public class Beds  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String hospitalName;
    
    
    
    
    private Double lat;
    
    
    
    
    private Double lng;
    
    
    
    
    private Integer remain;
    
    
    
    
    private Integer totalBeds;
    
    
    
    
    private String hpid;

    @PostPersist
    public void onPostPersist(){

    }

    public static BedsRepository repository(){
        BedsRepository bedsRepository = BedsApplication.applicationContext.getBean(BedsRepository.class);
        return bedsRepository;
    }

    public void initialize_beds(){
        System.out.println("테스트 중2----------");
        List<Beds> hospitalDataList = remainBedsApiParseXml("경기도", "분당구");

        if (hospitalDataList != null && !hospitalDataList.isEmpty()) {
            BedsRepository bedsRepository = repository();

            for (Beds bed : hospitalDataList) {
                bedsRepository.save(bed);
            }
        } else {
            System.out.println("No data found from API or an error occurred.");
        }
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    public List<Beds> remainBedsApiParseXml(String sido, String sigungu) {
        String xmlData = null;
        List<Beds> bedsDtoList = new ArrayList<>();
        try {
            System.out.println("테스트 중3----------");
            xmlData = getRemainbedsbySido(sido, sigungu);
            if (xmlData == null || xmlData.contains("<errMsg>") || xmlData.contains("<returnAuthMsg>")) {
                System.err.println("API 요청 중 오류 발생: " + xmlData);
                return null;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

            NodeList itemListNodes = doc.getElementsByTagName("item");

            for (int i = 0; i < itemListNodes.getLength(); i++) {
                Node itemListNode = itemListNodes.item(i);

                if (itemListNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemListElement = (Element) itemListNode;
                    Beds bedsDto = new Beds();
                    bedsDto.setHospitalName(getTagValue(itemListElement, "dutyName"));
                    bedsDto.setHpid(getTagValue(itemListElement, "hpid"));
                    // 임시로 일반 병상 가용 정보를 가져옴
                    bedsDto.setRemain(Integer.parseInt(getTagValue(itemListElement, "hvec")));
                    bedsDto = getHospitalLocationByhpid(bedsDto);
                    
                    System.out.println(bedsDto);
                    bedsDtoList.add(bedsDto);
                } else {
                    System.out.println("[ExternalApiService - EmbedsApiParseXml] 예상 하지 못한 에러 발생.");
                }
            }
        } catch (Exception exception){
            System.out.println(exception);
        }
        return bedsDtoList;
    }

    private String getPotalXmlData(String baseUrl, Map<String, String> params) throws Exception {
        String serviceKey = "ZpJ0Lq1T10c%2B8TqXfNjrszx74eWsrqKsIvTLnSDckkTwyVxJmwF2hHH5LDuFhl%2FqgY9Y7H5Aez%2F4KFp3Ild2qA%3D%3D";
        String urlStr = baseUrl + "?serviceKey=" + serviceKey;

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);

        for (Map.Entry<String, String> param : params.entrySet()) {
            urlStr += "&" + URLEncoder.encode(param.getKey(), "UTF-8") + "=" + URLEncoder.encode(param.getValue(), "UTF-8");
        }

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        return sb.toString();
    }

    private Beds getHospitalLocationByhpid(Beds beds) throws Exception {
        System.out.println("테스트 중4----------");
        String API_URL = "https://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytBassInfoInqire";
        Map<String, String> params = new HashMap<>();
        params.put("HPID", beds.getHpid());

        String xmlData = getPotalXmlData(API_URL, params);

        if (xmlData == null || xmlData.contains("<errMsg>") || xmlData.contains("<returnAuthMsg>")) {
            System.err.println("API 요청 중 오류 발생: " + xmlData);
            return null;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

        NodeList itemListNodes = doc.getElementsByTagName("item");
        for (int i = 0; i < itemListNodes.getLength(); i++) {
            Node itemListNode = itemListNodes.item(i);

            if (itemListNode.getNodeType() == Node.ELEMENT_NODE) {
                Element itemListElement = (Element) itemListNode;
                beds.setLat(Double.parseDouble(getTagValue(itemListElement, "wgs84Lat")));
                beds.setLng(Double.parseDouble(getTagValue(itemListElement, "wgs84Lon")));
                System.out.println("값을 주고 있다고!" + getTagValue(itemListElement, "wgs84Lon"));
                beds.setTotalBeds(Integer.parseInt(getTagValue(itemListElement, "hpbdn")));
            } else {
                System.out.println("[ExternalApiService - EmbedsApiParseXml] 예상 하지 못한 에러 발생.");
            }
        }

        return beds;
    }

    private String getRemainbedsbySido(String sido, String sigungu) throws Exception {
        String API_URL = "https://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmRltmUsefulSckbdInfoInqire";
        Map<String, String> params = new HashMap<>();
        params.put("STAGE1", sido);
        params.put("STAGE2", sigungu);

        return getPotalXmlData(API_URL, params);
    }


//<<< Clean Arch / Port Method
    public static void bedsUpdate(HospitalizationApproved hospitalizationApproved){
        
        repository().findById(hospitalizationApproved.getBedsId()).ifPresent(beds->{
            
            if (beds.getRemain() > 0) {
                beds.setRemain(beds.getRemain()-1);
                repository().save(beds);
            } else {
                // 보상 트랜젝션을 구현해야할지 모르겠다.
                System.out.println("남은 자리가 없습니다.");
            }

         });   
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void bedsUpdate(Discharged discharged){
        
        repository().findById(discharged.getBedsId()).ifPresent(beds->{
            
            if (beds.getRemain()+1 <= beds.getTotalBeds()){
                beds.setRemain(beds.getRemain()+1);
                repository().save(beds);
            } else {
                System.out.println("잘못된 데이터: 전체 병상보다 가용병상이 많을 수 없음.");
            }
         });

    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
