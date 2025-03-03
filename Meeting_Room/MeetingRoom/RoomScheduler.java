package Meeting;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoomScheduler {
	private Map<String,MeetingRoom> storage;
	
	RoomScheduler()
	{
		this.storage = new HashMap<>();
	}
	
	public void addMeetingRoom(MeetingRoom room) {
		
		storage.put(room.getRoomId(), room);
		System.out.println(room.getRoomId() + " added");
	}
	
	public void bookRoom(String roomId, EnumSet<RoomFeature> requiredFeatures) {
		MeetingRoom room = storage.get(roomId);
		
		if (room != null && room.getFeatures().containsAll(requiredFeatures)) {
			System.out.println("Room " + roomId + " booked successfully.");
		}
		else {
			System.out.println("Room " + roomId + " booking unsuccessfull.");
		}
	}
	
	public void listAvailableRooms(EnumSet<RoomFeature> requiredFeatures) {
		
		for(Map.Entry<String, MeetingRoom> map:storage.entrySet()) {
			
			if(map.getValue().getFeatures().containsAll(requiredFeatures)) {
				System.out.println("Available: "+map.getValue());
			}
			
		}
	}

}
