# NetToggle — WiFi + Mobile Data Floating Toggle

## Kya hai ye
Ek chhota overlay jisme 2 icon hote hain — WiFi aur Mobile Data.
Tap karo, turant ON/OFF ho jaayega. Status bar kheenchne ki zaroorat nahi.

**Zaroori: Phone ROOTED hona chahiye** (Magisk/su access), kyunki Android 10+
me normal apps WiFi aur Mobile Data ko bina root ke directly toggle nahi kar
sakte — ye `su` ke through `svc wifi` / `svc data` command chalata hai.

## Kaise chalayen (Android Studio)
1. Ye poora folder Android Studio me `Open` karo (File → Open → NetToggle folder).
2. Gradle sync hone do (pehli baar thoda time lagega, internet chahiye).
3. Phone USB se connect karo (USB debugging ON) ya emulator use karo (root wale emulator me hi data/wifi toggle chalega, normal emulator me sirf UI test hoga).
4. Run ▶ dabao — app phone me install ho jaayegi.

## App use karna
1. App kholo → **"Overlay Permission Allow karo"** button dabao → jo settings screen khule usme apni app ko allow karo, wapas aa jao.
2. **"Floating Icon Start karo"** dabao.
3. Su/root permission ka popup aayega (Magisk) — **Allow/Grant** karo.
4. Ab screen ke top-left corner me chhota sa dark box dikhega jisme WiFi
   aur Data ke icon honge. Isse kahin bhi drag karke move kar sakte ho.
5. Icon par ek tap = turant toggle. Bright icon = ON, halka/faded icon = OFF.
6. Ye overlay har app ke upar (games, browser, kahin bhi) dikhta rahega
   jab tak "Floating Icon Band karo" na dabao ya app se service stop na karo.

## Agar phone rooted nahi hai
Root ke bina Android khud kisi bhi normal app ko WiFi/Data force toggle
karne ki permission nahi deta (security restriction hai, Android 10 se).
Agar rooted nahi hai to sirf ek kaam ho sakta hai: tap karne par ek chhota
system panel (bottom sheet) khul jaye jisme dono toggle ho — status bar
poora kheenchna nahi padega, lekin ek extra tap lagega. Bologe to wo
alag (non-root) version bana deta hoon.

## Files
- `MainActivity.kt` — permission lene aur service start/stop karne ki screen
- `OverlayService.kt` — floating icon aur drag logic
- `RootUtils.kt` — root (`su`) commands chalane ka helper
