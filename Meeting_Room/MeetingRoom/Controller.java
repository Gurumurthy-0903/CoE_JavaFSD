package Meeting;

import java.util.EnumSet;

public class Controller {
	public static void main(String[] args) {
		RoomScheduler rs = new RoomScheduler();
		
		rs.addMeetingRoom(new MeetingRoom("001","Planning Room",20,EnumSet.of(RoomFeature.PROJECTOR,RoomFeature.AIR_CONDITIONING,RoomFeature.WHITEBOARD)));
		rs.addMeetingRoom(new MeetingRoom("002","Board Room",10,EnumSet.of(RoomFeature.PROJECTOR,RoomFeature.AIR_CONDITIONING)));
		rs.addMeetingRoom(new MeetingRoom("003","Strategy Room",15,EnumSet.of(RoomFeature.PROJECTOR,RoomFeature.AIR_CONDITIONING,RoomFeature.WHITEBOARD,RoomFeature.CONFERENCE_PHONE)));
		
		rs.bookRoom("001",EnumSet.of(RoomFeature.PROJECTOR,RoomFeature.AIR_CONDITIONING,RoomFeature.WHITEBOARD));
		
		rs.listAvailableRooms(EnumSet.of(RoomFeature.PROJECTOR,RoomFeature.AIR_CONDITIONING,RoomFeature.CONFERENCE_PHONE,RoomFeature.WHITEBOARD));
		
	}
}
