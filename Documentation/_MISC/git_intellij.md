## Etapes de développement sur Git

-> cloner le remote repository (en ligne) sur son poste local

-> on se positionnne (checkout) sur la branche qu'on veut utiliser comme base de travail
	(ex : local/application)
	
-> créer une branche de travail et checkout dessus 
	(ex : local/brancheTravail)

-> ajouter/modifiers des fichiers dans le repository en local

-> commit les modifications (on les sauvegarde sur la branche en local)

-> si besoin récupérer les commits d'autres branches en synchronisant les branches

-> push les modifications (la branche remote du même nom sera actualisée avec la branche locale) 
	(ex : remote/brancheTravail)
	
-> merge dans la branche depuis laquelle on a checkout (local/application)


## Utiliser Git dans IntelliJ

** Ajouter un projet Git existant dans IntelliJ **

	File -> New -> Project from version control

** Récupérer l'URL du projet sur github **

	Dans le repository du projet -> onglet Code -> bouton Code vert -> copier l'URL

** Pour créer une nouvelle branche (locale) **

	Onglet Git en bas à gauche -> liste des branches locales à gauche (la courante avec l'étiquette orange)
	Clic droit sur la branche depuis laquelle on veut travailler -> New Branch from selected
	Si l'option "Checkout" est cochée, la nouvelle branche devient la courante

** Faire des commits dessus **

	onglet commit à gauche
    OU   onglet Git en haut -> commit
    OU   petite coche verte dans la barre des tâches Git

** Pour push la nouvelle branche (locale) vers le remote **

	Simplement push dans l'onglet de commit
	(une nouvelle branche remote avec le même nom sera créée en auto)

** Pour récupérer une branche remote qui n'existe pas en local **

	Dans la liste des branches du remote
	-> clic droit -> checkout

** Pour synchroniser une branche locale avec une branche du remote **

	clic droit sur la branche locale -> checkout
	onglet Git dans le menu principal en haut -> pull -> sélectionner la branche remote qu'on veut

** Pour merge une branche dans la master (après le push) **

	clic droit sur la branche locale -> checkout
	synchroniser la branche locale avec celle où on veut la merge (si il y a eu des commits entre temps) 
	onglet Git dans le menu principal en heut -> merge
	choisir la branche où on veut merge (les branches commençant par origin/ pour les remotes)
	
** Pour supprimer une branche, quand tout le travail concernant la branche a été merge dans la master et qu'on n'ajoutera plus rien **

	checkout sur une autre branche
	clic droit sur la branche -> delete
	(pareil pour une branche locale ou remote)
	
** Penser à faire un git update avant de merge la branche de travail dans la master
	
	flèche descendante bleue dans la barre de tâches en haut