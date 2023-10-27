import java.time.LocalTime;
import java.util.*;


class Solution {
    public int solution(String[][] book_time) {
        List<Reservation> reservations = new ArrayList<>();
        
        Arrays
            .stream(book_time)
            .map(strings -> Reservation.of(strings[0], strings[1]))
            .sorted()
            .forEach(reservations::add);
        
        PriorityQueue<LocalTime> rooms = new PriorityQueue<>();
        for (Reservation reservation : reservations) {
            if (!rooms.isEmpty()
                &&
                !rooms
                    .peek()
                    .isAfter(
                        reservation.getStartTime()
                    )) {
                rooms.poll();
            }
            
            LocalTime endTime = reservation.getEndTime();
            
            LocalTime plusTime = reservation
                .getEndTime()
                .plusMinutes(10);
            if (plusTime.isBefore(endTime)) {
                plusTime = LocalTime.of(23, 59);
            }
            
            rooms.offer(
                plusTime
            );
        }
        return rooms.size();
    }
}

class Reservation implements Comparable<Reservation> {
    private final LocalTime startTime;
    private final LocalTime endTime;
    
    public Reservation(LocalTime startTime, LocalTime endTime) {
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);
        
        if (!(startTime.isBefore(endTime))) {
            throw new IllegalArgumentException("시작시간이 종료시간보다 늦습니다.");
        }
        
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public static Reservation of(String startTime, String endTime) {
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);
        
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        
        return new Reservation(start, end);
    }
    
    @Override
    public int compareTo(Reservation o) {
        return this.startTime.compareTo(o.startTime);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        
        Reservation that = (Reservation) o;
        
        if (!startTime.equals(that.startTime)) return false;
        return endTime.equals(that.endTime);
    }
    
    @Override
    public int hashCode() {
        int result = startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb
            .append("startTime=")
            .append(startTime);
        sb
            .append(", endTime=")
            .append(endTime);
        sb.append('}');
        return sb.toString();
    }
}