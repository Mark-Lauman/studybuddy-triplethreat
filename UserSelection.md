# Unit Test - User Selection Menu #


### Details ###

  * Presses "Cancel"
    1. User presses "New Player" Button
    1. Dialog box pops up with the text "Please enter the name of the new player".
    1. User presses "Cancel"
    1. Dialog box closes.

  * Presses close box
    1. User presses "New Player" Button
    1. Dialog box pops up with the text "Please enter the name of the new player".
    1. User presses "Cancel"
    1. Dialog box closes.

  * Empty username
    1. User presses "New Player" button
    1. Dialog box pops up with the text "Please enter the name of the new player"
    1. User leaves text field empty
    1. User presses "OK"
    1. Message box pops up saying that username is not valid

  * Different inputs of username
    1. User presses "New Player" button
    1. Dialog box pops up with the text "Please enter the name of the new player"
    1. User types in one of the following:
      * “Test” – Valid, no errors, continue
      * “Test User” – Valid, no errors, continue
      * “123” – Valid, no errors, continue
      * “   “ – Not Valid, message saying that username is an invalid username,
      * “1234Test” – Valid, no errors, continue
    1. Dialog box closes
    1. Entered text displays on the User Selection list
    1. A folder with the name equal to the entered text is created in ./Data/Users/ folder