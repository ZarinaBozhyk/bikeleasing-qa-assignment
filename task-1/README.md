# Claims test scenarios

These Gherkin files describe intake checks and the handling of theft,
damage, defect and wear claims.

## Files

| File | Purpose |
| --- | --- |
| [intake_checks.feature](features/intake_checks.feature) | Check claim existence, contract validity and the insured bike |
| [theft_claim.feature](features/theft_claim.feature) | Reject theft claims or approve them and record later recovery |
| [damage_claim.feature](features/damage_claim.feature) | Check partial payment and total loss |
| [defect_wear_claim.feature](features/defect_wear_claim.feature) | Route defect and wear claims to warranty |


## Reading the scenarios

- Given describes the starting conditions and submitted information.
- When describes the action being tested.
- Then describes the expected result.
- Background contains conditions shared by every scenario in that feature.
- TODO comments identify details that need clarification.

The intake feature checks the intake conditions directly.
The other features use successful intake as their starting point.

## Coverage and questions

- [Coverage matrix](coverage-matrix.md) lists the scenarios and expected results.
- [Clarification questions](questions.md) lists unresolved requirements.

## Execution

These files contain specifications only.
