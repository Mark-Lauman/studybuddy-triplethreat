# Unit Test - Buddy Selection menu #


### Details ###

  * Add Buddy - No file selected
    1. User presses "Add Buddy" button
    1. File chooser dialog pops up
    1. User clicks "Open" with no file selected
    1. Nothing happens

  * Add Buddy - Jar File with the correct files
    1. User presses "Add Buddy" button
    1. File chooser dialog pops up
    1. User chooses a jar file
    1. Clicks "Open"
    1. File chooser dialog closes
    1. Class file and Jar file is extracted to folder ./Buddies
    1. Buddy name is added to the list

  * Add Buddy - Jar file with incorrect files
    1. User presses "Add Buddy" button
    1. File chooser dialog pops up
    1. User chooses a jar file with incorrect files
    1. Clicks "Open"
    1. File chooser dialog closes
    1. Message box pops up saying it has incorrect files

  * Delete Buddy - No buddy selected
    1. User Clicks "OK"
    1. Message box pops upsaying not buddy selected

  * Delete Buddy - Buddy selected
    1. Selects a Buddy from the list
    1. User presses "Delete Buddy" button
    1. Confirmation box pops up
      * "Yes" - Buddy deleted from list, buddy folder deleted from ./Buddies
      * "No" - Confirmation box closes, nothing deleted

  * Export Buddy - No Buddy selected
    1. User clicks "Export Buddy"
    1. Message box pops up saying no buddy is selected

  * Export Buddy - Buddy Selected
    1. Selected buddy from list
    1. Pressed "Export Buddy"
    1. File Chooser box pops up
    1. Choosen file destination
    1. Clicked "Save" button
    1. Jar file of buddy saved to destination

  * Copy file - Internal function used by Add, Delete, Export
    * Works