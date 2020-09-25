# Assignment 2

## Reflections

- ### Computer Mouse

  Identify the types of testing you would perform on a computer mouse, to make sure that it is of the highest quality.

  1. Manual test of mouse buttons working.
  2. Manual test of mouse wheel working.
  3. Manual test of cable working.
  4. Manual test of pointing precision.
  5. Automatic buttons duration test.
  6. Automatic test of cable durability.

---

- ### Catastrophic failure

  Find a story where a software system defect had a bad outcome. Describe what happened. Can you identify a test that would have prevented it?

  #### The Peder Skram firing of the 'Hovsa-missile'.

  Apparently this, somewhat intelligent, Harpoon missile system was able to detect if the ship had turned between programming a missile and firing a missile.

  If the Navy Commander programmed Missile A and the ship turned some degrees, the system would automatically switch to Missile B, if this missile was better aligned.

  The firing of any missile would require a physical key inserted in the programming console. Thus turning the 'Fire' handle without the key would NOT lead to any missile being fired.

  But the missile switching algorithm was faulty and the automatically selected missile would not require a key for firing it. This led to the incident 1982 at Lumsås as the Navy Commander upon completion of the programming of Missile A turned the 'Fire' handle and launched Missile B.

  #### How to test:

  This might be a sign of multiple sources of truth. Somewhere in the system there must be at least one duplicate <code>fireMissile()</code>-method or there must have been a conditional in the single <code>fireMissile()</code>-method.

  If instead, a Mock or Spy was made of the component that had the <code>fireMissile()</code> method, the McDonnel Douglas software engineers could have evaluated the number of times, this method or the <code>checkForInsertedKey()</code>-method was called.

  This way they could have determined that firing a missile that was automatically selected by the system, would not call either the right <code>fireMissile()</code>-method or the <code>checkForInsertedKey()</code>.
