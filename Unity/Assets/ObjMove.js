#pragma strict

public var power : float = 6.5;
public var target : GameObject;

function Start () {
	target = GameObject.FindGameObjectWithTag("DeathZone");
}

function FixedUpdate () {
	var direction : Vector3 = target.transform.position - transform.position;
	GetComponent.<Rigidbody>().AddForce(direction.normalized * power);
	transform.LookAt(direction);
	
}