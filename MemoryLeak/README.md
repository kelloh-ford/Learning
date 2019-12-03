## MemoryLeak:

This repository contains an example of a memory leak in Android.

There are two activities in this project, and each activity has a button that
leads to the other.

In order to simulate the leak, navigate between both activities multiple times,
and then go back (using the back button in the simulator).

After a short while, a notification from LeakCanary will pop up, indicating that
a leak has occurred.


### Future Improvements:
Add more than one example of a memory leak, such as :
- Singleton leak
- Listener leak
and many more


### Reference materials:
https://proandroiddev.com/everything-you-need-to-know-about-memory-leaks-in-android-d7a59faaf46a
