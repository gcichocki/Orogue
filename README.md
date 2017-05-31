# Orogue
 Système multi-agents pour les jeux de stratégies.
 
 
 ## Problèmes liés à l'IA et solutions
 
 Aujourd'hui notre système est composé d'ennemis possédant une **machine à états**, capable de **communiquer deux à deux**.
 Nos agents peuvent partir à la **recherche** du joueur. La recherche peut-être orientée ou non (concept d'**estimation de la position du joueur** après l'avoir croisé).
 Les agents peuvent **poursuivre** le joueur lorsque qu'il est dans leur champ de vision et l'**attaquer** lorsqu'ils sont à son corps à corps.
 Cependant nos agents pourraient être améliorés et possèdent quelques faiblesses. Voici donc une liste de problèmes/points à améliorer par rapport à notre système multi-agents.

### Le social

Nos agents sont aujourd'hui seulement capable de s'échanger sous certaines conditions une structure de donnée utilisée pour l'**estimation de la position du joueur**.

Nous pensons cependant que la dimension sociale qui découle directement du principe de système multi-agents pourrait être approfondie. On pourrait par exemple imaginer la création de **groupes d'amis**, ou la création de liens (de relations) entre agents les amenant à se défendre les uns les autres.
 
La dimension sociale pourrait être exploitée de façon très diverse et pourrait rendre le système très intéressant à étudier.
 On peut par exemple imaginer des utilisations très amusante comme l'échange de '*numéros de téléphone*' permettant aux agents qui se sont déjà croisé et qui ont une bonne relation de communiquer à distance. Comme le montre cet exemple, avec de l'imagination, la dimension sociale pourra s'avérer être une ressource prometteuse.
 ### Multithreading
 
 Le plus gros problème d'optimisation que connaît notre système aujourd'hui est l'absence de multithreading pour les agents. S'il semblerait logique d'utiliser un thread par agent, dans le but d'améliorer les performances de notre système, nous avons finalement choisi de ne pas opter pour cette solution pour une raison majeure.
 
 En effet, grâce au multithreading, nos agents auraient pu pré-calculer leur chemin et travailler sur leur carte de probabilité en avance pour ne plus avoir qu'à bouger au moment de leur tour. Cependant nous recevons des informations d'Orogue à chaque fin de tour d'une unité. De fait si un agent doit faire un Astar et le calcule en avance (grâce au multithreading), le chemin trouvé ne sera peut-être plus valable au tour suivant. En effet comme d'autres agents se déplaceront sur la carte, et que les agents sont considérés comme des obstacles, un chemin peut comporter des cases inatteignables.                                                                                                                                        
 
 Nous avons réfléchi à une solution qui consisterait à calculer les chemins et lisser les cartes de probabilités à  l'avance pour profiter des avantages du multithreading tout en vérifiant en début de tour d'un agent qu'il n'a pas besoin de refaire ces calculs. Cependant nous n'avons pas eu le temps de mettre en place cette fonctionnalité.
 
 ### Stratégies
 
 Le système implémenté aujourd'hui est particulièrement aggressif. Un agent qui repère le joueur n'aura qu'une envie : l'éliminer. Les agents peuvent former des groupes grâce à la communication mais la dimension sociale n'est pas plus exploitée. 
 
 Cependant beaucoup d'autres stratégies pourraient être implémentée pour améliorer et diversifier le comportement de nos agents. 
 
 
 **Exemples :** 
 
    - Un agent pourrait se replier pour laisser sa place à un autre agent en cas de blessure grave
    - Un agent peut choisir de ne pas attaquer seul s'il considère que c'est dangereux
    - Un agent pourrait utiliser sa connaissance de la carte (présence de points d'intérêts pour le joueur comme les pizzas) pour faire des embuscades.
    
    
