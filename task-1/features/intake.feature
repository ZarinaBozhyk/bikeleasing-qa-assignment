@intake
Feature: Check claim intake conditions
  Intake checks whether the claim exists,
  whether the user's contract is valid,
  and whether the reported bike matches the insured bike.

  Rule: Intake checks identify whether each condition is met

    @IN-01
    Scenario: Confirm all intake conditions
      Given the claim exists in the system
      And the user's contract is valid
      And the reported bike is the bike insured under that contract
      When the intake conditions are checked
      Then the claim existence check confirms that the claim exists
      And the contract check confirms that the contract is valid
      And the bike check confirms that the reported bike matches the insured bike

    @IN-02
    Scenario: Identify a claim that does not exist
      Given the claim does not exist in the system
      When the claim's existence is checked
      Then the claim existence check confirms that the claim does not exist

    @IN-03
    Scenario: Identify an invalid contract
      Given the claim exists in the system
      And the user's contract is invalid
      When the contract's validity is checked
      Then the contract check confirms that the contract is invalid

    @IN-04
    Scenario: Identify a bike that does not match the insured bike
      Given the claim exists in the system
      And the user's contract is valid
      And the reported bike is not the bike insured under that contract
      When the reported bike is checked against the contract
      Then the bike check confirms that the reported bike does not match the insured bike