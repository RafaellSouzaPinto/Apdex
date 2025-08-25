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
│   └── Apdex.java          # Classe principal com implementação APDEX
└── test/java/org/example/
    └── ApdexTest.java      # Testes unitários JUnit
```

## Funcionalidades Implementadas

### Classe Apdex

- **Cálculo APDEX por contagens**: `computeApdexFromCounts(satisfied, tolerating, frustrated)`
- **Cálculo APDEX por amostras**: `computeApdexFromSamples(responseTimes, threshold)`
- **Classificação automática**: `classify(apdexScore)` e `classifyFromSamples()`
- **Validação de entrada**: Verificação de parâmetros válidos

### Classificações APDEX

- **EXCELLENT**: ≥ 0.94
- **GOOD**: ≥ 0.85
- **FAIR**: ≥ 0.70
- **POOR**: ≥ 0.50
- **UNACCEPTABLE**: < 0.50

## Testes Implementados

### Cobertura de Classificações

- ✅ EXCELLENT (0.94 - 1.0)
- ✅ GOOD (0.85 - 0.93)
- ✅ FAIR (0.70 - 0.84)
- ✅ POOR (0.50 - 0.69)
- ✅ UNACCEPTABLE (0.0 - 0.49)

### Casos de Teste

- **Dataset RM 555130**: Teste com total de amostras igual ao RM
- **Casos de borda**: Limites exatos de cada classificação
- **Validação de entrada**: Parâmetros inválidos e exceções
- **Casos extremos**: Arrays vazios, elementos únicos
- **Fórmula APDEX**: Verificação matemática da implementação
- **Testes de classificação específicos**: Exemplos práticos para cada nível APDEX

### Exemplos de Testes por Classificação

- **EXCELLENT**: `[50, 75, 90]` com T=100 → APDEX = 1.0
- **GOOD**: Dataset RM 555130 → APDEX = 0.90
- **FAIR**: `[50, 75, 90, 100, 500]` com T=100 → APDEX = 0.8
- **POOR**: `[100, 100, 400, 401, 1000]` com T=100 → APDEX = 0.5
- **UNACCEPTABLE**: `[0, 98, 2]` contagens → APDEX = 0.49


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