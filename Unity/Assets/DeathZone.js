#pragma strict

public static var dead : boolean;

function Start () {
	dead = false;
}

function Update () {

}

function OnTriggerEnter(col : Collider){
	if(col.gameObject.tag == "Hiyoko"){
		UIController.gameOver = true;
		dead = true;
	}
}	