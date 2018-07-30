Feature: Grab email
	Scenario Outline: Read Encrypted Email Through Virtru
	Given User is on login page
	When User enters valid "<username>" and "<password>"
	Then User logged in successfully
	When User open email with subject "<subject>"
	And User decrypts email
	When User open email with subject "<secondSubject>"
	Then User confirms message is "<expectedMessage>"
Examples:
	| username          | password  | subject       | secondSubject         | expectedMessage             |  
	|SRHirsch@gmail.com | 			 		| TEST				  |	Verify with Virtru on | we got to the email!        |