java-wol
========

A simple Java implementation of WOL(Wake-On-Lan).

##Usage
```java
  WOLNode node = new WOLNode("00224d846c99");
		try {
			node.wakeUP();
		} catch (UnableToWakeUpWOLNodeException e) {
			//TODO: write some code
		}
```

##MAC Address allowed formats
* 11:22:33:AA:BB:CC
* 11-22-33-AA-BB-CC
* 1122.33AA.BBCC
* 112233AABBCC

> **Note:** The letters can be both upper and lower case.

[![githalytics.com alpha](https://cruel-carlota.pagodabox.com/9a11e7b3eb110284978fb20b8a1d58f1 "githalytics.com")](http://githalytics.com/rmrodrigues/java-wol)
