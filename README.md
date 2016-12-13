# DialogueGame
This is the implementation for the paper:

Hai-Long Trieu, Hiroyuki Iida, Nhien Pham Hoang Bao, Le-Minh Nguyen, "Towards Developing Dialogue Systems with Entertaining Conversations", 
the 9th International Conference on Agents and Artificial Intelligence (ICAART 2017), Porto, Portugal, 2017


The website for the human evaluation:
http://150.65.242.105:8080/dialoguesystem/


Entertaining Dialogue System using Game Refinement Theory



INPUT
	- sfx-restaurant.json (full: 1006 dialogues)
	- sfx-restaurant-demo-1.json (1 dialogue)
	- sfx-restaurant-demo-4.json (4 dialogues)
	

METHODS
	1. Greetings
	2. Personal Topics
	3. Remove Grammar
	4. Entend by Keywords
	5. Feedback more detail to users
	6. Make surprising for users

	
HELP
	java -cp target/classes:target/lib/* Dialogue_GameRefinement help
	java -cp target/classes:target/lib/* Dialogue_GameRefinement [input-option] [rule-file-name] [method-option] [out-file-name]
	
	example: java -cp target/classes:target/lib/* Dialogue_GameRefinement demo1 rules.txt greeting greeting-output.txt
	
1-CHOOSE INPUT
	full
	demo1
	demo4
	inFileName

2-CHOOSE RULE FILE
	ruleFileName
	
3-CHOOSE METHODS
	show-input
	show-keywords
	greeting
	personal-topic
	grammar
	extend
	feedback
	surprising

4-CHOOSE OUTFILE NAME
