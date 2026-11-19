@theft
Feature: Settle a claim for a stolen bike
  A theft claim can be rejected or approved.
  When a claim is approved, a new bike is provided.
  If the originally stolen bike is reported as found after replacement,
  the claim outcome changes to "Recovered".

  Background:
    Given the claim exists in the system
    And the user's contract is valid
    And the reported bike is the bike insured under that contract

  Rule: Theft of a bike that was not locked properly is not paid by insurance

    @TH-01
    Scenario: Reject a theft claim because the bike was not locked properly
      # Assumption: This scenario checks the reported result only
      # specific lock types and verification methods are outside its scope.
      Given the bike is reported as "stolen"
      And the bike was not locked properly at the time of the theft
      And evidence of the bike's locking condition has been submitted
      When the claim is assessed
      Then the claim category is "Theft"
      And the evidence check confirms that the bike was "not locked" properly
      And the claim outcome is "Rejected"
      And the claim is "not paid" by insurance

  Rule: An eligible theft claim is approved and a new bike is provided

    @TH-02
    Scenario: Approve a covered theft claim and provide a new bike
      Given the bike is reported as "stolen"
      And the bike was locked properly at the time of the theft
      And the theft is covered by insurance
      And evidence of the bike's locking condition has been submitted
      And the other required evidence and bike value have been submitted
      When the claim is assessed
      Then the claim category is "Theft"
      And the evidence check confirms that the bike was "locked" properly
      And the submitted evidence and bike value are accepted
      And the claim outcome is "Approved"
      And the claim is "paid" by insurance
      And a new bike is provided

  Rule: A stolen bike can still be found after a new bike was provided

    @TH-03
    Scenario: Record recovery when the stolen bike is found after replacement
      # The scenario starts from an approved claim, so it does not depend on TH-02.
      Given the theft claim has the outcome "Approved"
      And a new bike has been provided
      When the bike is reported as "found"
      Then the claim outcome is "Recovered"
      # TODO: Clarify whether payment must be repaid after recovery
      # and who keeps the original and replacement bikes.
      # Assumption: This scenario checks the change to "Recovered" only;
      # repayment and ownership decisions are outside its scope.
