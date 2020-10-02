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

## Investigation of tools

- ### JUnit 5

  Investigate JUnit 5 (Jupiter).

  Explain the following, and how they are useful:

  - <code>@Tag</code>
    _- adds a tag so tests can easily by filtered upon test execution to include some tests and exclude others._
  - <code>@Disabled</code>
    _- disables a test._
  - <code>@RepeatedTest</code>
    _- repeats a test the specified number of times._
  - <code>@BeforeEach, @AfterEach</code>
    _- method is executed once before and after each test respectively._
  - <code>@BeforeAll, @AfterAll</code>
    _- method is executed once before and after all tests respectively.
    <br>
    <font color="green">**Note:**</font> the methods must be static._
  - <code>@DisplayName</code>
    _- Sets a custom display name for the method to be used in test reporting._
  - <code>@Nested</code>
    _- is a way of grouping related tests in a nested test class within an outer test class instead of making the outer test class bloated.<br><font color="red">**Caution:**</font> Java does not allow static methods in inner classes. Circumvent by adding <code>@TestInstance(Lifecycle.PER_CLASS)</code> to any nested test class._
  - <code>assumeFalse, assumeTrue</code>
    _- If assumptions (preconditions) are not met, the test will not run._

- ### Mocking frameworks

##### https://www.baeldung.com/mockito-vs-easymock-vs-jmockit

I read the above comparison of Mockito, EasyMock and jMockit.

They all seem to have many similarities and follow the same pattern - more or less - of record-replay-verify, i.e.: record what should happen if some operation is carried out and verify that it indeed happened.

Some may favour one flavour over another. As I am not a seasoned tester, I can't yet tell when to prefer one mocking framework over the next.<br>
So in short, having seen Mockito in class, I would prefer to go with Mockito.
