using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class endTime : MonoBehaviour {

	public static string endTimeStr = "0";
	Text text;
	// Use this for initialization
	void Start () {
		text = GetComponent<Text>();
	}
	
	// Update is called once per frame
	void Update () {
		text.text = "endTime : " + endTimeStr;
	}
}
