------------------------------------------------------------------------
-- SEQUENCE: {name}
-- {description}
--
-- 最后修改人：
-- 最后修改日期：{date}
------------------------------------------------------------------------
DROP SEQUENCE {name};

CREATE SEQUENCE {name}
  START WITH {minValue}
  MAXVALUE {maxValue}
  NOCYCLE
  CACHE 20
  ORDER;