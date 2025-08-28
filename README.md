# Bad Adventure Game — JavaFX (GUI)

A small JavaFX RPG where you explore a **10×10** grid of rooms, **search** for gold, **fight or run** from monsters, and **heal** by sleeping.  
The UI is fully built with **FXML** and images; game logic is handled in a single controller.

## Demo & Screenshots
- **Start Screen**  
  ![Start Screen](.assets.1.png)

- **Intro / Instructions**  
  ![Intro Screen](docs/images/intro-screen.png)

- **Game Screen**  
  ![Game Screen](docs/images/game-screen.png)

  ## Gameplay Overview

- The map is a **10×10 grid** of rooms (`Room[][]`).
- Each **move** (North, East, West, South) changes your position unless a room is **blocked**.
- **Search** a room to try to find gold; sometimes a **monster** appears.
- If a monster is present, choose to **Fight** or **Run**.  
- **Sleep** to heal back to **20 HP** (with a small chance a monster finds you).
- The right-hand **log** (`TextArea showMoves`) shows all events.
- On death, use **Try Again** to reset state and continue.

## Core Rules

### Player
- Starts with **20 HP**; **Strength / Dexterity / Intelligence** are random (3d6).
- **Gold** starts at 0 and increases when found.
- Damage dealt on a hit: **`player.strength / 3`** (integer division).

### Rooms
- Each room spawns with:
  - **Gold**: random **10–100** at creation.
  - **Blocked**: ~**25%** chance the exit is blocked.

### Search
- Roll **d20** (`1–20`).
- **Success** if roll **< player.intelligence**:
  - Collect any **gold** (room gold becomes 0).
  - **50%** chance a **monster appears**.
- **Failure**: announce **monster** presence immediately.

### Monster
- Stats from dice (2×(2d6) for STR/DEX/INT), **10 HP** baseline.
- **Fight**:
  - Roll **d20**; **hit** if **≥ monster.dexterity**.
  - On hit, monster takes **`player.strength / 3`**.
  - Monster may retaliate for **`monster.strength / 2`** (chance-based).
- **Run**:
  - Chance-based; you might take **`monster.strength / 2`** damage while escaping.

### Sleep (Heal)
- If **HP < 20**: restore to **20**.
- Else: small chance a **monster** appears and deals a small hit; otherwise you rest safely.

- # Screenshots Playing the Game
- 
