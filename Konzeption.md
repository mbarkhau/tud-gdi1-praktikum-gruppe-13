Mir gehen beim durchlesen der Aufgabenstellung einige Sachen durch den Kopf.

Player hat einige Primitive die wir im Controller zu Spielzügen abstrahieren könnten.
z.B. Tor schießen, Ball vom Gegenspieler abnehmen, Zu Spieler xy passen, usw.

Der Controller sollte ein Objekt haben, indem der Zustand des Spielers erfasst wird (kann auch der Spieler selbst sein). d.h Position, Richtung, Verfassung.

Wir sollten auch das Spielfeld modellieren, mit dem wir Voraussagen über Positionen der Mitspieler und des Balls machen können.

Der Controller könnte eine Strategie haben (austauschbar) womit der nächste Spielzug ermittelt wird. (Hier sitzen die Zustandsautomaten)

Verschiedene Spieler haben verschiedene Strategien, wie Torwart, Stürmer, Verteidiger.
Kann, ggf. erweitert werden und im Spiel ausgetauscht werden, falls es die Spielsituation erfordert.

Bei der Stürmer Strategie, könnte z.B. ermittelt werden, wie gut die Chancen auf ein Tor stehen (Entfernung vom Tor, Position der Mitspieler, Spieler hat noch Kraft), und kann sich entscheiden aufs Tor zu schießen, zu Passen, oder näher zum Tor zu laufen.

Da die Anzahl der Nachrichten unter den Spielern Beschränkt, sollten möglichst nur bestimmte Spieler (Kapitän), Hauptstürmer, Befehle sagen können.

Es ist von Rauschen in den Nachrichten die Rede, man sollte untersuchen, wie dieser aussieht, und ob man da Redundanz in den Nachrichten einbauen kann.
Nachrichten sollten sich auch an bestimmte Spieler richten können. Das Format sollte man je nach Rauschen bestimmt werden (vielleicht spielt Entfernung vom Sprecher auch eine Rolle).

Naja, ist alles Optional, wir haben ja schließlich nur zwei Wochen. Insgesamt scheint das Spiel ja eher so das drumherum zu sein, die eigentliche Aufgabenstellung sind ja eher die Automaten.