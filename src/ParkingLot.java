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
    //该构造函数将根据指定的楼层数和每层的停车位数量，初始化一个包含不同类型停车位的停车场对象
    ParkingLot(String parkingLotId, int nfloors, int noOfSlotsPerFlr) {
        this.parkingLotId = parkingLotId;
        slots = new ArrayList<>();//初始化一个新的 ArrayList，用于存储每层楼的停车位
        
        for (int i = 0; i < nfloors; i++) { //循环遍历每一层楼
            slots.add(new ArrayList<>()); //为每层楼创建一个新的 ArrayList 并添加到 slots 列表中。
            List<Slot> floorSlots = slots.get(i); //获取当前楼层的停车位列表
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike")); //依次在当前楼层添加一个类型为 "truck" 的停车位,添加两个类型为 "bike" 的停车位
            for (int j = 3; j < noOfSlotsPerFlr; j++) {
                slots.get(i).add(new Slot("car")); //循环添加剩余的停车位，类型为 "car"
            }
        }
    }
    //parkVehicle此方法的作用是根据车辆类型在停车场中找到一个空闲的合适停车位，并返回停车票据ID。如果没有找到合适的停车位，则返回 null。
    public String parkVehicle(String type, String regNo, String color) {
        Vehicle vehicle = new Vehicle(type, regNo, color);
        for (int i = 0; i < slots.size(); i++) { //外层循环，遍历每层楼的停车位列表。
            for (int j = 0; j < slots.get(i).size(); j++) { //内层循环，遍历当前楼层的每个停车位。
                Slot slot = slots.get(i).get(j); //获取当前停车位
                if (slot.type == type && slot.vehicle == null) { //检查停车位的类型是否与车辆类型匹配，并且停车位是否为空
                    slot.vehicle = vehicle; //将车辆停放在停车位上
                    slot.ticketId = generateTicketId(i + 1, j + 1);//生成停车票据ID，并赋值给停车位
                    return slot.ticketId;
                }
            }
        }
        System.out.println("NO slot available for given type"); //没有找到合适的停车位
        return null;
    }
    
    //这个方法的作用是为每个停车操作生成一个唯一的票据ID，以便后续查找和管理
    private String generateTicketId(int flr, int slno){ //通过 generateTicketId(i + 1, j + 1) 生成停车票据ID
        return parkingLotId + "_" + flr + "_" + slno;
    }
    //unPark，getNoOfOpenSlots两个方法结合起来，实现了停车场中车辆的移除和空闲停车位数量的统计功能
    public void unPark(String ticketId){
        String[] extract = ticketId.split("_");//将票据ID按下划线 _ 分割成数组
        int flr_idx=Integer.parseInt(extract[1])-1;//从票据ID中提取楼层索引，并将其转换为整数（从1基数改为0基数）
        int slot_idx=Integer.parseInt(extract[2])-1;//从票据ID中提取停车位索引，并将其转换为整数（从1基数改为0基数）
        for(int i=0; i<slots.size();i++){
            for(int j=0;j<slots.get(i).size(); j++){ //嵌套循环遍历所有楼层和每层的停车位，找到与提取的楼层和停车位索引匹配的停车位
                if(i==flr_idx && j==slot_idx) {
                    Slot slot = slots.get(i).get(j);
                    slot.vehicle=null;
                    slot.ticketId=null;
                    System.out.println("Unparked vehicle"); //将匹配的停车位的 vehicle 和 ticketId 置为空，并输出 "Unparked vehicle"
                }
            }
        }
    }
    
    int getNoOfOpenSlots(String type){
        int count=0; //初始化计数器
        for(List<Slot> floor: slots){ //外层循环遍历所有楼层的停车位列表
            for(Slot slot: floor){ //内层循环遍历每层楼的停车位
                if(slot.vehicle == null && slot.type.equals(type)) count++; //检查停车位是否为空并且类型匹配，若满足条件则计数器加1
            }
        }

        return count;//返回计数器的值，即特定类型的空闲停车位数量
    }
    
    //displayOpenSlots，displayOccupiedSlots两个方法通过遍历 slots 列表，分别输出特定类型的空闲和已占用停车位信息
    void displayOpenSlots(String type){ //遍历所有楼层和每层楼的停车位，满足条件，输出该停车位所在的楼层和编号
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
