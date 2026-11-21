@intake
Feature: Check claim intake conditions
  At first notice of loss, intake checks whether the claim exists,
  whether the user's contract is valid,
  and whether the reported bike matches the insured bike.
  Only a claim that passes all three checks proceeds to triage and coverage.

  Rule: A claim that passes all intake checks proceeds to triage

    @IN-01
    Scenario: Confirm all intake conditions
      Given the claim exists in the system
      And the user's contract is valid
      And the reported bike is the bike insured under that contract
      When the intake conditions are checked
      Then the claim existence check confirms that the claim exists
      And the contract check confirms that the contract is valid
      And the bike check confirms that the reported bike matches the insured bike
      And the claim proceeds to triage

  Rule: A claim that fails an intake check does not proceed to triage

    # Assumption: the diagram shows only the path for a passed intake,
    # so a failed check is assumed to stop the claim before triage.
    # TODO: Clarify what happens next (rejection, request for documents, manual review).
    # See questions 1–3 in questions.md.

    @IN-02
    Scenario: Stop a claim that does not exist
      Given the claim does not exist in the system
      When the intake conditions are checked
      Then the claim existence check confirms that the claim does not exist
      And the claim does not proceed to triage

    @IN-03
    Scenario: Stop a claim with an invalid contract
      Given the claim exists in the system
      And the user's contract is invalid
      When the intake conditions are checked
      Then the contract check confirms that the contract is invalid
      And the claim does not proceed to triage

    @IN-04
    Scenario: Stop a claim for a bike that does not match the insured bike
      Given the claim exists in the system
      And the user's contract is valid
      And the reported bike is not the bike insured under that contract
      When the intake conditions are checked
      Then the bike check confirms that the reported bike does not match the insured bike
      And the claim does not proceed to triage