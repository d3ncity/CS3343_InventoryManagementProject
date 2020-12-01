# CS3343_Inventory/Warehouse Management Project
## CityU HK CS3343 Software Engineering Practices Course-work project

### Description of the application
- This system can manage items of different dimensions (in cubic metre dimensions) in various slots (with cubic metre sizes).
- Items that cannot be added to the Warehouse on a particular day is put into a Queue, and further put into the Warehouse when possible.
- Periodically, the Warehouse will optimize its storage slots by rearranging the items in the best possible manner.

### For Users:
### Installation
1.


### For Developers:
### Please note that in addition to the JRE System Library, the following JAR files / Libraries were used:
- JUnit 5 (for testing)
- Log4j and Jcabi and the Eclipse Plugin ANSI Escape in Console (for logging)
- Maven Project in Eclipse


### Usage Guide:

1. First, a user is required to choose the input method, either through **File Input or System Input**.
2. If a user choose the file input method, the user is required to input the **file path** for the command file (Text file is recommended). The available command is listed in the "List of commands" section. For example, if a user need to add an item, the command file should contain a line of "addItem|4|14-Oct-2020", indicate the command of adding an item of size 4, deliver at 14-Oct-2020.
***Please be noted that system date should be set before a user create a slot, and a slot should be created before an item is added. Or else exceptions will be thrown.***
3. However, if the system input is selected, then the user will only need to follow the prompt instructions and type the representing number of the command when prompted. For details, please refer to "list of command" section.
4. No matter what method a user is using (file input method or system input method), the user is always manipulating the same warehouse system. As the methods are interrelated, the changes made in either method can also been seen in the system.
5. In addition, for the optimization command, a user can manually input the optimization command and turn the automatic optimization off by using "changeStatus" command (Not recommend). In normal situation, the warehouse will automatically optimize the warehouse when the warehouse is half full. So it is not necessary to use the manually optimize command.
6. Last but not least, all other commands can be found in the next section, there are three types of command which serve the purpose in warehouse operation, visualization, and system settings.
7. Lastly, if the warehouse requires reset, a user can reset through system command input. (Highly not recommended)


### List of commands
The following list is represented in the following format:
(Representing number) Description [File command]
(1) Add Slot [addSlot|(Size)]
(2) Add Item [addItem|(Size)|(DeliveryDate)]
(3) Manually Optimize [optimize]
(4) Visualize [visualize]
(5) List Slot By ID [listSlotByID|(ItemID)]
(6) List Item By ID [listItemByID|(SlotID)]
(7) List Warehouse Details [listWarehouse]
(8) Undo [undo] **Note: not every command can undo**
(9) Redo [redo] **Note: not every command can redo**
(10) System Reset **Not available in file input method**
(11) (For Demo) Set Date [setNewDay|(Date)]
(12) (For Demo) Change Auto Optimization Status [changeStatus]
(13) EXIT **Not available in file input method**



### Versions and corresponding Bug Fixes

#### Version 1.0
-Denial of negative volume valued items.
-Terminate the execution of items that have the delivery date earlier than the arrival date.
#### Version 2.0
-Process item that should be delivered up to this date.
-Necessity to check if the given date is valid or not (54-Oct-2014 or 29-Feb-2021).  
#### Version 3.0
-Lack of the process of checking for the expiration of items in the queue.
-Exception Handling needed for the CmdListItemByID.
-Items added from queue to the warehouse is limited to the number of items left instead of limiting it to the volume released by those items.

## List of Contributors:
- Denny
- Daniyar
- Anirudh
- Harvey
- Kalys
- Nursultan
