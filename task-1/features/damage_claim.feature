@damage
Feature: Settle a claim for a damaged bike
  Damage can result in partial payment or a total loss.
  Wear parts are excluded from partial payment.

  Background:
    Given the claim exists in the system
    And the user's contract is valid
    And the reported bike is the bike insured under that contract
    And the damage is covered by insurance

  Rule: Partial payment covers eligible repair costs but excludes wear parts

    @DA-01
    Scenario: Exclude wear parts from a partially paid damage claim
      Given the bike is reported as "damaged"
      And the assessment data describes a bike that is worth repairing
      And the repair estimate includes both covered repair costs and wear parts
      And the required evidence and bike value have been submitted
      When the claim is assessed
      Then the claim category is "Damage"
      And the submitted evidence and bike value are accepted
      And the bike is "worth" repairing
      And the claim outcome is "Partial"
      # TODO: Clarify which parts count as wear parts and how the payment is calculated.
      # Assumption: This scenario checks which costs are covered or excluded;
      # exact amounts, deductibles and limits are outside its scope.
      And the claim is "partially paid" by insurance
      And the payment covers the eligible repair costs
      But the payment does not cover wear parts

  Rule: Damage that is not worth repairing is treated as a total loss

    @DA-02
    Scenario: Settle damage that is not worth repairing as a total loss
      Given the bike is reported as "damaged"
      And the assessment data describes a bike that is not worth repairing
      And the required evidence and bike value have been submitted
      When the claim is assessed
      Then the claim category is "Damage"
      And the submitted evidence and bike value are accepted
      And the bike is "not worth" repairing
      # TODO: Clarify how "not worth repairing" is determined and how total loss is paid.
      # Assumption: This scenario checks the assessment result and insurance payment;
      # repair thresholds, valuation formulas and payment amounts are outside its scope.
      And the claim outcome is "Total loss"
      And the claim is "paid" by insurance