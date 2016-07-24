using UnityEngine;
using System.Collections;
using System.Diagnostics;

public class Manager : MonoBehaviour {
	
//	Stopwatch sw;
	
	private static AndroidJavaObject _plugins;
	public string PhoneNumber = "010";
	
	//
	public static bool end = false;
	public static int cnt = 0;

	public void keyreceive(string keyevent){
		
	}

	void Start(){
		Screen.orientation = ScreenOrientation.Landscape;
		Screen.sleepTimeout = SleepTimeout.NeverSleep;
	}

	void Awake(){
		AndroidJavaClass Ajc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
		_plugins = Ajc.GetStatic<AndroidJavaObject> ("currentActivity");

	}
	
	void OnGUI(){

	}

	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown (KeyCode.Escape)) {
			_plugins.Call ("QuitApp");
		}

		if (end) {
			if(cnt == 0){
				endTime.endTimeStr = System.DateTime.Now.ToString ("yyyy/MM/dd hh:mm:ss");
				_plugins.Call ("getSignUpTime", signUpTime.signUpTimeStr);
				_plugins.Call ("getPlayTime", playTime.playTimeStr);
				_plugins.Call ("getEndTime", endTime.endTimeStr);
//				Object[] obj = new Object[3];
//				obj[0] = signUpTime.signUpTimeStr;
//				obj[1] = playTime.playTimeStr;
//				obj[2] = endTime.endTimeStr;

//				_plugins.Call("getInfo", obj);
				cnt++;
			}
		} else {

		}
			
	}

	public void addFriend(){
		_plugins.Call ("addFriend");
	}
	public void quitApp(){
		_plugins.Call ("QuitApp");
	}
	public void ranking(){
		_plugins.Call ("rangking");
	}
	public void Present(){
		_plugins.Call ("present");
	}
	
	public void AndroidPhoneNum(string arg){
		PhoneNumber = arg;
		endTime.endTimeStr = PhoneNumber;
	}
	
	public void FinishApp(string arg){
		System.Diagnostics.Process.GetCurrentProcess ().Kill ();
	}

}
