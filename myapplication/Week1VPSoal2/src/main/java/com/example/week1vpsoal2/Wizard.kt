package com.example.week1vpsoal2

class Wizard(var name: String) {

    var hp = 50
    var maxHp = 50

    var mana = 30
    var maxMana = 30

    var kills = 0

    var healthPotions = 5
    var manaPotions = 5

    var strongWizard = false
    var lifesteal = 0

    fun showStats() {
        println("\n===== ${name}'s STATS =====")
        println("HP: $hp/$maxHp")
        println("Mana: $mana/$maxMana")
        println("Kills needed to evolve: $kills/5")
        println("Mana Potions held: $manaPotions")
        println("Health Potions held: $healthPotions")

        if (strongWizard) {
            println("Lifesteal: $lifesteal")
        }
    }

    fun drinkManaPotion() {
        if (manaPotions <= 0) {
            println("You have no Mana Potion.")
            return
        }

        if (mana == maxMana) {
            println("Your mana is already full.")
            return
        }

        manaPotions--
        mana += 15

        if (mana > maxMana) {
            mana = maxMana
        }

        println("Mana restored.")
    }

    fun drinkHealthPotion() {
        if (healthPotions <= 0) {
            println("You have no Health Potion.")
            return
        }

        if (hp == maxHp) {
            println("Your HP is already full.")
            return
        }

        healthPotions--
        hp += 25

        if (hp > maxHp) {
            hp = maxHp
        }

        println("HP restored.")
    }

    fun rename() {
        print("Enter new name: ")

        val newName = readLine() ?: ""

        if (newName.isBlank()) {
            println("Error: Name cannot be empty.")
            return
        }

        name = newName

        println("Your name is now $name.")
    }

    fun evolve() {
        if (kills >= 5 && !strongWizard) {
            strongWizard = true

            maxHp = 75
            maxMana = 45

            hp = maxHp
            mana = maxMana

            lifesteal = 1

            println("\n===== EVOLUTION =====")
            println("You became a Strong Wizard!")
            println("HP: $maxHp")
            println("Mana: $maxMana")
            println("Lifesteal: $lifesteal")
        }
    }

    fun reset() {
        hp = 50
        maxHp = 50

        mana = 30
        maxMana = 30

        kills = 0

        healthPotions = 5
        manaPotions = 5

        strongWizard = false
        lifesteal = 0
    }
}