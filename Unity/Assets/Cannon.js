#pragma strict

public var prefab: GameObject;
public var power : float;
function Start () {

}

function Update () {
	if(Input.GetMouseButtonDown(0)){
		var bullet = LoadBullet();
		
		var ray:Ray = Camera.main.ScreenPointToRay(Input.mousePosition);
		
		var dir : Vector3 = ray.direction.normalized;
		
		bullet.GetComponent.<Rigidbody>().velocity = dir * power;
	}
		
}

function LoadBullet() : GameObject{
	var bullet = GameObject.Instantiate(prefab) as GameObject;
	bullet.transform.parent = transform;
	bullet.transform.localPosition = Vector3.zero;
	return bullet;
}