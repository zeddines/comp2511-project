## Danny - test static entities 

# Wall
tests to check no entities share position with wall 
movement tests:
- player cannot walk into wall
- enemy cannot walk into wall
- boulder cannot move into wall 

# Exit 
player walking into exit ends game 
enemy walking into exit does nothing -- assumption? 
boulder cannot move into the exit (assumption?) 
- do boulders exist in games with exits? 

# Boulder --> can go into boulderTests 
acts like a wall
player cannot walk into 
enemy cannot walk into
player can push boulder 
player position, boulder position
new player position = boulder position 
new boulder position = boulder position + 1 (x/y coord)
floor switch behaviour can go into boulder tests 

# Switch 
player can appear on switch 
enemy can appear on switch
boulder can appear on switch 
boulder/switch behaviour can go into boulder tests 

# Basic door no key 
acts like a wall 
door/key interaction can go into item using/not using tests 