using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class signUpTime : MonoBehaviour {

	public static string signUpTimeStr = "0";
	Text text;


	// Use this for initialization
	void Start () {
		text = GetComponent<Text>();
		signUpTimeStr = System.DateTime.Now.ToString("yyyy/MM/dd hh:mm:ss");
		text.text = "signUpTime : " + signUpTimeStr;
	}
	
	// Update is called once per frame
	void Update () {


	}
}
