package sae.transform;

import sae.dungeon.Dungeon;
import sae.dungeon.Room;
import sae.graph.Node;
import sae.dungeon.Direction;
import sae.graph.GraphSoluce;
import sae.dungeon.DungeonSoluce;
import java.util.*;

public class Dungeon2Graph {

    private Map<Room, Node> roomToNodeMap;

    public Dungeon2Graph(Dungeon dungeon) {
        roomToNodeMap = new HashMap<>();
        buildGraph(dungeon);
    }

    private void buildGraph(Dungeon dungeon) {
        for (Room room : dungeon.getRooms()) {
            roomToNodeMap.put(room, new Node(room.getCoords().getX(), room.getCoords().getY()));
        }

        for (Room room : dungeon.getRooms()) {
            Node currentNode = roomToNodeMap.get(room);
            for (Map.Entry<sae.dungeon.Direction, Room> entry : room.getNextRooms().entrySet()) {
                Room connectedRoom = entry.getValue();
                Node connectedNode = roomToNodeMap.get(connectedRoom);
                if (connectedNode != null) {
                    currentNode.addNeighbor(connectedNode);
                }
            }
        }
    }

    public Node mappedNode(Room room) {
        return roomToNodeMap.get(room);
    }

    public DungeonSoluce transform(GraphSoluce graphSoluce) {
        DungeonSoluce dungeonSoluce = new DungeonSoluce();

        List<Node> path = graphSoluce.getPath();
        for (int i = 0; i < path.size() - 1; i++) {
            Node currentNode = path.get(i);
            Node nextNode = path.get(i + 1);
            Direction direction = getDirectionFromNodes(currentNode, nextNode);
            if (direction != null) {
                dungeonSoluce.addDirection(direction);
            }
        }

        return dungeonSoluce;
    }

    private Direction getDirectionFromNodes(Node currentNode, Node nextNode) {

        int dx = nextNode.getX() - currentNode.getX();
        int dy = nextNode.getY() - currentNode.getY();

        if (dx == 1 && dy == 0) return Direction.EAST;
        if (dx == -1 && dy == 0) return Direction.WEST;
        if (dx == 0 && dy == 1) return Direction.NORTH;
        if (dx == 0 && dy == -1) return Direction.SOUTH;

        return null;
    }
}
