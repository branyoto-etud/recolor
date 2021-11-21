# RECOLOR2

---
## TODO List

-[x] Color conversion test
-[ ] Programs arguments
    - input_folder (default: images **or** img)
    - output_folder (default: input_folder + "processed" + first positive integer not used)

#### Section Palette
-[ ] Save/Load -> Input box (class on itself that can be instantiated with a string: the name of the box)
-[ ] Color box display -> Controller + ColorBox??
-[ ] Scrollbar
-[ ] Pick a color -> update Section "Color Selection"
-[ ] Scrollbar updated when on keyboard move

#### Section Color Selection
-[ ] Swap button HSV/RGB
-[ ] Sliders for RGB/HSV & alpha
-[ ] Hex input box -> update new color and sliders
-[ ] Checkbox/Toggleable button for display of slider's label
-[ ] Checkbox/Toggleable button for display current value
-[ ] Auto Update hex and slider when the value is modified

#### Section Preview
-[ ] Recolor images at runtime (heavy)
-[ ] Wheel of images synchronized (original/processed)
-[ ] Save button
-[ ] Image add -> Update color Palette

---
## Controls
###Mouse
- Left Click: Main action (color/image selection, slider update, ...)
- Right Click: Set new color to the color below at mouse position

###Keyboard

- Tab : Change section

#### Section Palette

- Ctrl + s : Save current palette
- Ctrl + l : Load palette 
- ↑ ↓ → ← : Change selected color (auto update or wait Space/Enter to be pressed ???)

#### Section Color Selection

- ↑ ↓ : Select another slider
- → ← : Move the slider value
- Ctrl + → : Increase slider value by 10
- Ctrl + ← : Decrease slider value by 10
- Ctrl + h : Swap between hex box and sliders
- Ctrl + m : Toggle color mode

#### Section Preview

- Ctrl + s : Save images
- \+ : Add new image
- ↑ ↓ : Select previous/next image