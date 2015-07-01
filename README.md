# lein-crustacean

A Leiningen plugin to do many wonderful things.

## Usage

Suppose you have defined an entity
(ns some.namepspace.user
  (:require [crustacean.core :as c :refer [defentity]))

(defentity user
  (:migration-file "resources/migrations/user.edn")
  (:fields [email :string :fulltext :indexed :unique-identity :assignment-required]
           [name :string :fulltext :indexed :unique-value :assignment-required]))


```
lein migrate some.namespace.user
```

You can also migrate multiple entities at once

```
lein migrate some.namespace.user some.namespace.shopping-cart
```

## License

Copyright © 2015 Canary Computer Corporation

Distributed under the Apache License version 2.0
