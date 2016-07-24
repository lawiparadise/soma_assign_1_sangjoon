using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class playTime : MonoBehaviour {

	//
	long startTime = System.DateTime.Now.Ticks;
	public static string playTimeStr = "0";

	//
	Text text;

	//
	public static int cnt;

	// Use this for initialization
	void Start () {
		text = GetComponent<Text>();
		cnt = 0;
	}
	
	// Update is called once per frame
	void Update () {
		if (Manager.end) {
			if(cnt == 0){
				endTime.endTimeStr = System.DateTime.Now.ToString ("yyyy/MM/dd hh:mm:ss");
				cnt++;
			}

		} else {
			long nowTime = System.DateTime.Now.Ticks;
			double timeGap = (nowTime - startTime) / 10000000.0f;
			playTimeStr = timeGap.ToString("F1");
			text.text = "playTime : "+playTimeStr;
		}

	}
}
