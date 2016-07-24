#pragma strict

public var sceneName : String;

function Start () {

}

function Update () {

}

function LoadGame(){
	UIController.gameOver = false;
	Application.LoadLevel(sceneName);
	Score.score = 0;
}