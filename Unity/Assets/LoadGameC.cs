using UnityEngine;
using System.Collections;

public class LoadGameC : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void Load(){
		playTime.cnt = 0;
		endTime.endTimeStr = "0";
		Manager.end = false;
		Manager.cnt = 0;
	}
}
