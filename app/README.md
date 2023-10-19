# Adhan

## App Description
This Android app is designed to display Muslim prayer times and schedule Adhan notifications based on user preferences.

## Components
- **MainActivity**: The main activity for user interaction.
  - `+displayPrayerTimes()`
  - `+scheduleAdhan()`

- **PrayerTimesApp**: The core class that coordinates app functionality.
  - `+setUserPreferences()`
  - `+notifyPrayerTime()`

- **NotificationService**: Handles scheduling and displaying Adhan notifications.
  - `+scheduleNotification()`
  - `+displayNotification()`

- **PrayerTimes**: Manages prayer times data.
  - `+fetchPrayerTimes()`
  - `+savePrayerTimes()`

- **UserPreferences**: Stores and manages user settings.
  - `+setAdhanSound()`
  - `+setPrayerTimes()`

- **AdhanSound**: Represents available Adhan sounds.
  - attributes: available, selected, default adhan sounds
  - methods:
    - getavailableadhansounds
    - getselectedadhansound
    - setselectedadhansound
    - getdefaultadhansound
    - playadhansound
```java
public void setSelectedAdhanSound(String selectedAdhanSound) {
    // Update the selected sound attribute
    this.selectedAdhanSound = selectedAdhanSound;
    
    // optional
    playAdhanSound(selectedAdhanSound);
}
```
- **Database**: Handles storage and retrieval of data.
  - savePrayerTimes(prayerTimesData): Save the retrieved prayer times data to the database. This method should take prayer times data as input and store it in the database for later retrieval.
  - getPrayerTimes(): Retrieve the saved prayer times data from the database. This method should return the prayer times data stored in the database.
  - saveUserPreferences(userPrefs): Save user preferences, such as Adhan sound selection and notification settings, to the database.
  - getUserPreferences(): Retrieve user preferences from the database. This method should return the user preferences stored in the database.
  - clearData(): Optionally, provide a method to clear all stored data in the database, which can be useful if the user wants to reset their preferences or if you need to update the data.

- **AlarmScheduler**: Manages scheduling of alarms.
  - Methods related to alarm scheduling.
  - convert mp3 or wav to OGG (standard android alarm sound)?
  - "assets" directory or "res/raw"
```java
Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Prayer Time Alarm"); // Alarm label
intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true); // Bypass the alarm setting UI
intent.putExtra(AlarmClock.EXTRA_VIBRATE, false); // Disable vibration
intent.putExtra(AlarmClock.EXTRA_RINGTONE, customAdhanSoundUri); // URI of custom Adhan sound
```
```java
alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, initialTriggerTime, intervalMillis, pendingIntent);
alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, initialTriggerTime, intervalMillis, pendingIntent);
alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
alarmManager.setAlarmClock(alarmInfo, pendingIntent);
```

## UML-like Code

```plaintext
@startuml
class MainActivity {
    +displayPrayerTimes()
    +scheduleAdhan()
}
class PrayerTimesApp {
    +setUserPreferences()
    +notifyPrayerTime()
}
class NotificationService {
    +scheduleNotification()
    +displayNotification()
}
class PrayerTimes {
    +fetchPrayerTimes()
    +savePrayerTimes()
}
class UserPreferences {
    +setAdhanSound()
    +setPrayerTimes()
}
class AdhanSound {
    +getAvailableAdhanSounds()
    +getSelectedAdhanSound()
    +setSelectedAdhanSound(soundInfo)
    +getDefaultAdhanSound()
    +playAdhanSound()
    +addCustomAdhanSound(soundInfo)
    +removeCustomAdhanSound(soundInfo)
    +getCustomAdhanSounds()
    +setDefaultAdhanSound(soundInfo)
    +validateAdhanSound(soundInfo)
}
class Database {
    +savePrayerTimes(prayerTimesData)
    +getPrayerTimes()
    +saveUserPreferences(userPrefs)
    +getUserPreferences()
    +clearData()
}
class AlarmScheduler {
    +setExact()
    +setRepeating()
    +setInexactRepeating()
    +setAndAllowWhileIdle()
    +setExactAndAllowWhileIdle()
    +setAlarmClock()
}
MainActivity --> NotificationService
MainActivity --> AlarmScheduler
MainActivity --> AdhanSound
MainActivity --> UserPreferences

PrayerTimesApp --> UserPreferences
PrayerTimesApp --> NotificationService
PrayerTimesApp --> AlarmScheduler
PrayerTimesApp --> AdhanSound

NotificationService --> AlarmScheduler
NotificationService --> MainActivity
NotificationService --> AdhanSound

UserPreferences --> AdhanSound
UserPreferences --> AlarmScheduler
UserPreferences --> PrayerTimes

Database --> PrayerTimes
Database --> UserPreferences

AdhanSound --> AlarmScheduler

@enduml

```
![image](https://github.com/billgec/adhan/assets/99565818/2562e9f1-7d6a-4b46-8fd3-c0a49105a462)

# Ideas
- set notification minutes before adhan 
- select which days to play adhan
- prayer helper (step by step with virtual imam)
- automatic location reader 
