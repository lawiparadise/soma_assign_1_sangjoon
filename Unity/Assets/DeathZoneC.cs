using UnityEngine;
using System.Collections;

public class DeathZoneC : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnTriggerEnter(Collider other){
		if(other.transform.tag == "Hiyoko"){ //충돌한 물체 tag가 Player라면, End 씬으로 씬을 넘긴다. 
			Manager.end = true;
		}
	}

}
