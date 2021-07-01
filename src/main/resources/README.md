Если property "layer" и "pattern" отсутствуют, то аспект не инициализируется.
Можно использовать как отдельно "layer" и "pattern", так и совместно.

```
layers:
    - controller = execution(* ru.clevertec..*controller*..*(..))
    - service = execution(* ru.clevertec..*service*..*(..))
    - repository = execution(* ru.clevertec..*repository*..*(..))
    - all = execution(* ru.clevertec..*(..))
```

