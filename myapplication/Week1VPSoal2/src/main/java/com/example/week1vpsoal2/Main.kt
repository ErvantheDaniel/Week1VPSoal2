package com.example.week1vpsoal2

class Main(val wizard: Wizard) {

    var enemyType = ""
    var enemyHp = 50
    var enemyMaxHp = 50

    fun startBattle() {
        val randomNumber = (1..3).random()

        if (randomNumber == 1) {
            enemyType = "Fire"
        } else if (randomNumber == 2) {
            enemyType = "Water"
        } else {
            enemyType = "Grass"
        }

        enemyHp = 50
        enemyMaxHp = 50

        battle()
    }

    fun attack(type: String) {
        if (wizard.mana < 10) {
            println("Not enough mana.")
            return
        }

        wizard.mana -= 10

        var damage = 10

        if (wizard.strongWizard) {
            damage = 15
        }

        if (
            type == "Fire" && enemyType == "Grass" ||
            type == "Water" && enemyType == "Fire" ||
            type == "Grass" && enemyType == "Water"
        ) {
            damage *= 2
        }

        println("${wizard.name} uses $type Attack.")
        println("Damage: $damage")

        enemyHp -= damage

        if (enemyHp <= 0) {
            println("${enemyType}mon defeated.")

            wizard.kills++

            if (wizard.strongWizard) {
                wizard.lifesteal++
            }

            println("Kills: ${wizard.kills}/5")

            wizard.evolve()
            return
        }

        println("${enemyType}mon attacks.")

        wizard.hp -= 10

        if (wizard.strongWizard) {
            wizard.hp += wizard.lifesteal

            if (wizard.hp > wizard.maxHp) {
                wizard.hp = wizard.maxHp
            }

            println("Lifesteal restored ${wizard.lifesteal} HP.")
        }

        if (wizard.hp <= 0) {
            die()
        }
    }

    fun drinkPotion() {
        println("\n1. Health Potion")
        println("2. Mana Potion")

        print("Choose potion: ")

        when (readLine()) {
            "1" -> wizard.drinkHealthPotion()
            "2" -> wizard.drinkManaPotion()
            else -> println("Error: Invalid potion choice.")
        }
    }

    fun die() {
        println("\n===== YOU DIED =====")
        println("Your character will restart.")

        wizard.reset()

        println("Character reset.")
    }

    fun battle() {
        while (wizard.hp > 0 && enemyHp > 0) {
            println("\n===== BATTLE =====")
            println(wizard.name)
            println("HP: ${wizard.hp}/${wizard.maxHp}")
            println("Mana: ${wizard.mana}/${wizard.maxMana}")
            println("HP Potions: ${wizard.healthPotions}")
            println("MP Potions: ${wizard.manaPotions}")

            println("\n${enemyType}mon")
            println("HP: $enemyHp/$enemyMaxHp")
            println("Type: $enemyType")

            println("\na. Fire Attack")
            println("b. Water Attack")
            println("c. Grass Attack")
            println("d. Drink potion")
            println("e. Run")

            print("Choose: ")

            when (readLine()) {
                "a" -> attack("Fire")
                "b" -> attack("Water")
                "c" -> attack("Grass")
                "d" -> drinkPotion()
                "e" -> {
                    println("You ran away.")
                    return
                }

                else -> println("Error: Invalid choice.")
            }
        }
    }

    fun menu() {
        while (true) {
            println("\n===== WIZARD ADVENTURE =====")
            println("What're you going to do?")
            println("1. View Stats")
            println("2. Enter battle")
            println("3. Exit")

            print("Choose: ")

            when (readLine()) {
                "1" -> {
                    wizard.showStats()

                    println("\na. Drink Mana Potion")
                    println("b. Drink Health Potion")
                    println("c. Rename self")
                    println("d. Back")

                    print("Choose: ")

                    when (readLine()) {
                        "a" -> wizard.drinkManaPotion()
                        "b" -> wizard.drinkHealthPotion()
                        "c" -> wizard.rename()
                        "d" -> {
                        }

                        else -> println("Error: Invalid choice.")
                    }
                }

                "2" -> startBattle()

                "3" -> {
                    println("Goodbye!")
                    return
                }

                else -> println("Error: Invalid choice.")
            }
        }
    }
}

fun main() {
    println("What's your name? : ")

    val name = readLine() ?: ""

    if (name.isBlank()) {
        println("Error: Name cannot be empty.")
        return
    }

    println("Good luck, $name! You're gonna need it!")

    val wizard = Wizard(name)
    val game = Main(wizard)

    game.menu()
}