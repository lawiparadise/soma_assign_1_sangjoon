#pragma strict

import UnityEngine.UI;

public var text : Text;
public static var gameOver : boolean;
public var button : GameObject;

function Start () {
	text.enabled = false;
	button.SetActive(false);
}

function Update () {
	if(gameOver){
		text.enabled = true;
		button.SetActive(true);
	}
}