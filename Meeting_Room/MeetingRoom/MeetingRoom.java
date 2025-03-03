package Meeting;

import java.util.EnumSet;

public class MeetingRoom {
	private String roomId;
	private String roomName;
	private int capacity;
	private EnumSet<RoomFeature> features;
	
	public MeetingRoom(String roomId, String roomName, int capacity, EnumSet<RoomFeature> features) {

		this.roomId = roomId;
		this.roomName = roomName;
		this.capacity = capacity;
		this.features = features;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public EnumSet<RoomFeature> getFeatures() {
		return features;
	}

	public void setFeatures(EnumSet<RoomFeature> features) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		return "MeetingRoom [roomId=" + roomId + ", roomName=" + roomName + ", capacity=" + capacity + ", features="
				+ features + "]";
	}
	
	
	

}
