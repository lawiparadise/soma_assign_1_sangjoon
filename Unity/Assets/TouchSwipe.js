#pragma strict

function Start () {

}

function Update () {
	var deltaPos : Vector2 = Input.GetTouch(0).deltaPosition / 100;
	
	var pos : Vector3 = transform.localPosition;
	pos.x = pos.x - deltaPos.x;
	pos.y = pos.y - deltaPos.y;
	
	transform.localPosition = pos;
}