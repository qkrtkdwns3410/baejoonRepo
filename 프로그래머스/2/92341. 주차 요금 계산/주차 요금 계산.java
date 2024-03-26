import java.util.*;

class Solution {
    public Map<String, Integer> parkingTimeMap = new HashMap<>();
    public Map<String, Integer> inTimeMap = new HashMap<>();
    
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        //누적 주차 - 기본 시간 이하, 기본요금
        // 누적 주차 기본 시간 초과 = 기본 요금 + 단위시간 * 단위요금 (나누어떨어지지않으면 올림)
        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");
            
            String[] times = record[0].split(":");
            
            int minutes = toMinutes(times);
            
            String carName = record[1];
            String status = record[2];
            
            System.out.println(Arrays.toString(record));
            
            //입차하는 경우
            if (Objects.equals(InOut.IN.name(),status)) {
                //inTime 에 기록한다.
                inTimeMap.put(carName, minutes);
            } else { //출차하는 경우
                int parkingTime = minutes - inTimeMap.remove(carName);
                parkingTimeMap.put(carName, parkingTimeMap.getOrDefault(carName, 0) + parkingTime);
            }
        }
        
        //처리되지 않은 요금 체크
        inTimeMap.forEach((carName, time)->{
            int parkingTime = (23*60 + 59)  - time;
            parkingTimeMap.put(carName, parkingTimeMap.getOrDefault(carName, 0) + parkingTime);
        });
        
        //기본시간
        int baseTime = fees[0];
        //기본 요금
        int baseFee = fees[1];
        //단위 시간
        int unitTime = fees[2];
        //단위 요금
        int unitFee = fees[3];
        
        //요금 계산
        TreeMap<String, Integer> sortedMap = new TreeMap<>();
        
        parkingTimeMap.forEach((carName, time)->{
            //기본 시간
            if(time <= baseTime){
                sortedMap.put(carName, baseFee);
            }else{
                //기본 시간 초과
                int fee = baseFee + (int)(Math.ceil((double)(time - baseTime) / unitTime)) * unitFee;
                sortedMap.put(carName, fee);
            }
        });
        
        System.out.println(sortedMap);
        
        
        return sortedMap.values().stream().mapToInt(i -> i).toArray();
    }
    
    private int toMinutes(String[] times) {
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }
    
    public enum InOut {
        IN, OUT;
    }
}
