## 1.3.1

Features:
  - Added calculating muzzle energy to the chronograph

## 1.3.0

Bug Fixes:


Features:
  - Components now have component specific details ( e.g. bullet shape/weight, powder shape, case length ect)

## 1.2.0 (2013-10-16)

Bug Fixes:
  - Fixed lots of bugs around saving logs
  - Fixed tons of little bugs that were found while making some changes
  - Can now delete recipes that have logs or batches associated with them
  - Fixed a lot of the response codes for most of the REST interface
 
Features:
  - Logs are greatly improved, along with adding new fields (like Firearm used, Group size, Target Distance, Range)
  - Added Chronograph details to logs. Each chronograph stores a set of FPS and will return back High, Low, Average, Std Deviation, and Variance
  - Hugely refactored how Recipes/Components/Logs interfaces with the exposed REST objects.
  - Cost is now included in the base recipe request, instead of being a seperate request.
  - Added flag to recipe for Compressed Loads

## 1.1.1 (2013-10-04)

Bug Fixes:
  - Fixed a missing entry for notes to logs when updating a log entry

## 1.1.0 (2013-10-04)

Features:
  - Changes for the Notes of a specific recipe to record a lot more data (Including Firearm used, Range location, Group size and Number of shots in group, target distance)
  - Added a notes field for individual batches to include any notes related to a batch.
