#pragma strict

import UnityEngine.UI;

public static var score : int;
private var text : Text;

function Start () {
	text = GetComponent.<Text>();
}

function Update () {
	text.text = "Score :" + score.ToString();
}