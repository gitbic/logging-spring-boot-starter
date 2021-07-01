Значения "layer", эквивалентое "patterns"
```
logging-service.aspectsProperties.layers:
    controller  =  execution(* ru.clevertec..*controller*..*(..))
    service     =  execution(* ru.clevertec..*service*..*(..))
    repository  =  execution(* ru.clevertec..*repository*..*(..))
    all         =  execution(* ru.clevertec..*(..))
```
По умолчанию прописаны настройки логирования для слоёв: controller, service, repository

Можно использовать как отдельно "layer" и "pattern", так и совместно. Если property "layer" и "pattern" отсутствуют, то аспект не инициализируется.


