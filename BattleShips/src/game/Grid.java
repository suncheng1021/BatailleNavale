package game;

import game.messages.HitResult;
import game.ships.*;

import java.util.ArrayList;
import java.util.Iterator;


public class Grid {

	public static final int SIZE = 10;
	private ArrayList<Ship> ships; // Liste des bateaux

	Grid() {
		this.ships = new ArrayList<Ship>();
		
		// On crée les bateaux, il ne sont pas encore placés sur la grille
		Ship ships[] = {new  ShipAircraftCarrier(), new ShipBattleship(), new ShipDestroyer(), new ShipSubmarine(), new ShipPatrolBoat()};

		// On place les bateaus sur la gille
		for (int i=0; i<ships.length; i++)
		{
			do {
				ships[i].setX((int) (Math.random() * (10)));
				ships[i].setY((int) (Math.random() * (10)));
				ships[i].setOrientation(Ship.Orientation.values()[(int)(Math.random() * 2)]);
			} while (!this.add(ships[i]));
			System.out.println(ships[i].toString() + ": x=" + ships[i].getX() + " y=" + ships[i].getY() + " " + ships[i].getOrientation());
		}
		
	}

	// vérifier que la position sur la grille est libre
	public boolean isFree(int x, int y) {
		if (this.getShip(x, y) == null)
			return true;
		return false;
	}

	// Peut placer un bateau ?
	public boolean canPlace(Ship ship) {

		// Est ce que le bateau tient sur la grille ?
		if ((ship.getOrientation() == Ship.Orientation.Horizontal) && ((ship.getX() + ship.getSize() > Grid.SIZE) || (ship.getY() >= Grid.SIZE)))
			return false;
		else if ((ship.getOrientation() == Ship.Orientation.Vertical) && ((ship.getY() + ship.getSize() > Grid.SIZE))  || (ship.getX() >= Grid.SIZE))
			return false;
		
		// Est ce que la position n'est pas déjà occupée ?
		for (int i=0; i < ship.getSize(); i++) 
			if ((ship.getOrientation() == Ship.Orientation.Horizontal) && !isFree(ship.getX() + i, ship.getY()))
				return false;
			else if (ship.getOrientation() == Ship.Orientation.Vertical && !isFree(ship.getX(), ship.getY() + i))
				return false;
		
		return true;
	}
	
	// ajoute un bateau si c'est possible
	public boolean add(Ship ship) {
		if (canPlace(ship)) {
			this.ships.add(ship);
			return true;
		}
		return false;	
	}
	
	public Ship getShip(int x, int y) {
		Iterator<Ship> iterator = this.ships.iterator();
		Ship ship;
		while (iterator.hasNext())
		{
			ship = iterator.next();
			if (ship.isAtPosition(x, y))
				return ship;
		}
		return null;
	}

	// Juste pour la console W pour eau sinon première du bateau en majuscule (minuscule si touché)
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int j = 0; j < Grid.SIZE; j++) {
			for (int i = 0; i < Grid.SIZE; i++)
				if ((isFree(i, j)))
					string.append("W ");
				else
					string.append(this.getShip(i, j).isHit(i, j)?this.getShip(i, j).toString().toLowerCase():this.getShip(i, j).toString());
			string.append("\n");
		}
		return string.toString();
	}
	
	// bateaux tous coulés ?
	public boolean isEnd() {
		Iterator<Ship> iterator = this.ships.iterator();
		while (iterator.hasNext())
			if (!iterator.next().isSunk()) return false;
		return true;
	}
	
	// frappe la gille à la position x y
	public HitResult hit(int x, int y) {
		// HitResult(nomBatteau, touché ?, coulé?, finPartie ?)
		Ship ship = this.getShip(x, y);
		if (ship != null) {
			return new HitResult(ship.getName(), ship.hit(x, y), ship.isSunk(), this.isEnd());
		}
		return new HitResult(null, false, false, false);
	}
}
