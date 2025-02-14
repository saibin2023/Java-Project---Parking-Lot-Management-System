import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    String parkingLotId;
    List<List<Slot>> slots;
/* 
List<List<Slot>> slots; 代表一个二维列表（或称为列表的列表），用于存储停车场中所有楼层的停车位信息。
List<Slot> 表示一个停车位列表，每个列表包含一个楼层的所有停车位。
List<List<Slot>> 表示一个包含多个楼层停车位列表的列表，即整个停车场的所有停车位。
具体来说，每个 List<Slot> 对应一个楼层，而 slots 则包含了所有楼层的停车位列表。
*/
    ParkingLot(String parkingLotId, int nfloors, int noOfSlotsPerFlr) {
        this.parkingLotId = parkingLotId;
        slots = new ArrayList<>();
        for (int i = 0; i < nfloors; i++) {
            slots.add(new ArrayList<>());
            List<Slot> floorSlots = slots.get(i);
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));
            for (int j = 3; j < noOfSlotsPerFlr; j++) {
                slots.get(i).add(new Slot("car"));
            }
        }
    }

    public String parkVehicle(String type, String regNo, String color) {
        Vehicle vehicle = new Vehicle(type, regNo, color);
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.type == type && slot.vehicle == null) {
                    slot.vehicle = vehicle;
                    slot.ticketId = generateTicketId(i + 1, j + 1);
                    return slot.ticketId;
                }
            }
        }
        System.out.println("NO slot available for given type");
        return null;
    }
    private String generateTicketId(int flr, int slno){
        return parkingLotId + "_" + flr + "_" + slno;
    }
    public void unPark(String ticketId){
        String[] extract = ticketId.split("_");
        int flr_idx=Integer.parseInt(extract[1])-1;
        int slot_idx=Integer.parseInt(extract[2])-1;
        for(int i=0; i<slots.size();i++){
            for(int j=0;j<slots.get(i).size(); j++){
                if(i==flr_idx && j==slot_idx) {
                    Slot slot = slots.get(i).get(j);
                    slot.vehicle=null;
                    slot.ticketId=null;
                    System.out.println("Unparked vehicle");
                }
            }
        }
    }
    int getNoOfOpenSlots(String type){
        int count=0;
        for(List<Slot> floor: slots){
            for(Slot slot: floor){
                if(slot.vehicle == null && slot.type.equals(type)) count++;
            }
        }

        return count;
    }
    void displayOpenSlots(String type){
        for(int i=0;i<slots.size();i++){
            for(int j=0;j<slots.get(i).size();j++){
                Slot slot=slots.get(i).get(j);
                if(slot.vehicle == null && slot.type.equals(type)) 
                    System.out.println("Floor " + (i+1) + " slot " + (j+1));
            }
        }   
    }
    void displayOccupiedSlots(String type){
        for(int i=0;i<slots.size();i++){
            for(int j=0;j<slots.get(i).size();j++){
                Slot slot=slots.get(i).get(j);
                if(slot.vehicle != null && slot.type.equals(type)) 
                    System.out.println("Floor " + (i+1) + " slot " + (j+1));
            }
        }   
    }
}
