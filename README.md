# APDEX - Teste Unitário com Abordagem TDD

## Descrição

Projeto Java com Maven e JUnit implementando o cálculo e classificação APDEX (Application Performance Index) seguindo a metodologia TDD (Test-Driven Development).

## Autor

**Rafael Souza Pinto - RM 555130**

## Tecnologias

- **Java 17**
- **Maven 3.x**
- **JUnit 5 (Jupiter)**
- **IntelliJ IDEA**

## Estrutura do Projeto

```
src/
├── main/java/org/example/
│   └── Apdex.java          # Implementação com métodos calcularApdex*
└── test/java/org/example/
    ├── ApdexCategoryTest.java  # Testes de categorias (Excelente, Bom, ...)
    └── ApdexTest.java          # Testes de cálculo por valores e bordas
```

## Funcionalidades Implementadas

### Classe Apdex (padrão da aula)

- `calcularApdex(int s, int to, int ta)`
- `calcularApdexExcelente(int s, int to, int ta)`
- `calcularApdexBom(int s, int to, int ta)`
- `calcularApdexRazoavel(int s, int to, int ta)`
- `calcularApdexRuim(int s, int to, int ta)`
- `calcularApdexInaceitavel(int s, int to, int ta)`
- `classificarApdex(int s, int to, int ta)`

## Testes Implementados

- `ApdexCategoryTest` cobre limites e classificação textual
- `ApdexTest` cobre valores representativos e casos de borda

### Exemplos de Testes por Classificação

- **EXCELLENT**: `calcularApdex(94, 6, 100) -> 0.97`
- **GOOD**: `calcularApdex(90, 0, 100) -> 0.90`
- **FAIR**: `calcularApdex(70, 10, 100) -> 0.75`
- **POOR**: `calcularApdex(50, 20, 100) -> 0.60`
- **UNACCEPTABLE**: `calcularApdex(0, 50, 100) -> 0.25`

### Annotations JUnit

- **@BeforeAll**: Preparação do dataset grande (555130 amostras)
- **@Test**: Todos os métodos de teste unitário

## Como Executar

### Pré-requisitos

- JDK 17
- Maven 3.x
- IntelliJ IDEA (recomendado)

### Executar Testes

```bash
# Via Maven
mvn test

# Via IntelliJ
# Clique direito em ApdexTest.java → Run 'ApdexTest'
```

### Build do Projeto

```bash
mvn clean compile
```

## Fórmula APDEX

```
APDEX = (Satisfied + Tolerating/2) / Total_Samples

Onde:
- Satisfied: Respostas ≤ T (threshold)
- Tolerating: T < Respostas ≤ 4T
- Frustrated: Respostas > 4T
```

## Critérios de Entrega ✅

- [x] Projeto Java com Maven e JUnit
- [x] Total de amostras = RM 555130
- [x] Nome e RM na primeira linha de cada classe
- [x] Uso de @BeforeAll para contexto apropriado
- [x] Cobertura de todas as classificações APDEX
- [x] Testes passando com sucesso no IntelliJ + JDK 17

### Logs de Teste Esperados

```
✅ sampleBased_apdexAndClassification_withRmDataset
✅ countsBased_classification_excellent
✅ countsBased_classification_good
✅ countsBased_classification_fair
✅ countsBased_classification_poor
✅ countsBased_classification_unacceptable
✅ sampleBased_boundary_includesEquals
✅ sampleBased_fair_classification_example
... (todos os testes passando)
```

## Repositório GitHub

[Apdex Repository](https://github.com/RafaellSouzaPinto/Apdex.git)
